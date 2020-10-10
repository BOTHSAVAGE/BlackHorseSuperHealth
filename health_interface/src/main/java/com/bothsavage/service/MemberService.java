package com.bothsavage.service;

import com.bothsavage.pojo.Member;

import java.util.List;

/**
 * 会员操作接口
 */
public interface MemberService {
    public Member findByTelephone(String telephone);
    public void add(Member member);
    public List<Integer> findMemberCountByMonths(List<String> months);
}
