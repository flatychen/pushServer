package cn.flaty.pushAdmin.services.message;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

import org.springframework.stereotype.Service;

@Service
public class PushMessageEhcachePool  {

	Cache messagePool = null;

	public PushMessageEhcachePool() {
		messagePool = new Cache(
				  new CacheConfiguration("testCache", Integer.MAX_VALUE)
				    .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)
				    .eternal(false)
				    .timeToLiveSeconds(3600)
				    .timeToIdleSeconds(3600));
	}

}
