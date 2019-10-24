package com.hobart.zookeeper.registry;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.hobart.zookeeper.generator.IncrementIdGenerator;
import com.hobart.zookeeper.properties.AliyunProperties;
import com.hobart.zookeeper.properties.HobartProperties;
import com.hobart.zookeeper.properties.ZookeeperProperties;
import com.hobart.zookeeper.registry.base.CoordinatorRegistryCenter;
import com.hobart.zookeeper.registry.base.RegisterDto;
import com.hobart.zookeeper.registry.zookeeper.ZookeeperRegistryCenter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册中心工厂.
 *
 * @author zhangliang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RegistryCenterFactory {

	private static final ConcurrentHashMap<HashCode, CoordinatorRegistryCenter> REG_CENTER_REGISTRY = new ConcurrentHashMap<>();

	/**
	 * 创建注册中心.
	 *
	 * @param zookeeperProperties the zookeeper properties
	 *
	 * @return 注册中心对象 coordinator registry center
	 */
	public static CoordinatorRegistryCenter createCoordinatorRegistryCenter(ZookeeperProperties zookeeperProperties) {
		Hasher hasher = Hashing.md5().newHasher().putString(zookeeperProperties.getZkAddressList(), Charsets.UTF_8);
		HashCode hashCode = hasher.hash();
		CoordinatorRegistryCenter result = REG_CENTER_REGISTRY.get(hashCode);
		if (null != result) {
			return result;
		}
		result = new ZookeeperRegistryCenter(zookeeperProperties);
		result.init();
		REG_CENTER_REGISTRY.put(hashCode, result);
		return result;
	}

	/**
	 * Startup.
	 *
	 * @param hobartProperties the paascloud properties
	 * @param host                the host
	 * @param app                 the app
	 */
	public static void startup(HobartProperties hobartProperties, String host, String app) {
		CoordinatorRegistryCenter coordinatorRegistryCenter = createCoordinatorRegistryCenter(hobartProperties.getZk());
		RegisterDto dto = new RegisterDto(app, host, coordinatorRegistryCenter);
		Long serviceId = new IncrementIdGenerator(dto).nextId();
		IncrementIdGenerator.setServiceId(serviceId);
		registerMq(hobartProperties, host, app);
	}

	private static void registerMq(HobartProperties hobartProperties, String host, String app) {
		CoordinatorRegistryCenter coordinatorRegistryCenter = createCoordinatorRegistryCenter(hobartProperties.getZk());
		AliyunProperties.RocketMqProperties rocketMq = hobartProperties.getAliyun().getRocketMq();
		String consumerGroup = rocketMq.isReliableMessageConsumer() ? rocketMq.getConsumerGroup() : null;
		String namesrvAddr = rocketMq.getNamesrvAddr();
		String producerGroup = rocketMq.isReliableMessageProducer() ? rocketMq.getProducerGroup() : null;
		coordinatorRegistryCenter.registerMq(app, host, producerGroup, consumerGroup, namesrvAddr);
	}
}
