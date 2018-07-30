package cn.mark.o2o.service;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestJedis {
	@Test
	public void testJedis() {
		// 1.设置IP地址跟端口号
		Jedis jedis = new Jedis("192.168.124.22", 6379);
		// 2.保存数据
		jedis.set("name", "wangrun");
		// 3.获取数据
		String name = jedis.get("name");
		// 4.数据展示
		System.out.println(name);
		// 5.关闭连接
		jedis.close();

	}
}
