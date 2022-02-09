package com.kunpeng.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class MyRandomRule extends AbstractLoadBalancerRule {
    public MyRandomRule() {
    }
    private AtomicInteger total = new AtomicInteger(0);
    private AtomicInteger currentIndex = new AtomicInteger(0);

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers();
                List<Server> allList = lb.getAllServers();
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }


//                int index = this.chooseRandomInt(serverCount);
//                server = (Server)upList.get(index);
                // 自定义负载均衡算法，每循环5次换下一个服务
                if (total.get() < 5){
                    server = upList.get(currentIndex.get());
                    total.incrementAndGet();
                }else{
                    total.set(0);
                    currentIndex.incrementAndGet();
                    if (currentIndex.get() >= upList.size()){
                        currentIndex.set(0);
                    }
                    server = upList.get(currentIndex.get());
                }



                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
