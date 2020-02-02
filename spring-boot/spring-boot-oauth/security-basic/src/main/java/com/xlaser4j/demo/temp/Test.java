package com.xlaser4j.demo.temp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @package: com.xlaser4j.demo.temp
 * @author: Elijah.D
 * @time: 2020/2/2 14:22
 * @description:
 * @modified: Elijah.D
 */
public class Test {
    /**
     * $2a$10$TCv100F1Xu.mMoxKAHa.HeaMJus49mlfwjuUJGWnLWQWHjDNZKxdi
     * $2a$10$zQYRGu6uAelTp56CQdwXa.cn7t4ROrbG/fpiiEBqmzdCNO04boQl.
     * $2a$10$TEgBeQtillRKk36lDOVCEeCQFdWqaNK1YlRUZfSKW8HOdXocYkbsm
     * $2a$10$jVou21g7RZnEacfbOIw0puyp3/hLkaSbd/y6Z.Ismj2WgULyiBdHW
     * $2a$10$tgm9/1L1C5rby2OfUoOlu.PiTe0nZcJJpNPNRPkEylkVALZdMyNSm
     *
     * @param args
     */
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        int count = 5;
        for (int i = 0; i < count; i++) {
            System.out.println(encoder.encode("1234"));
        }
    }
}
