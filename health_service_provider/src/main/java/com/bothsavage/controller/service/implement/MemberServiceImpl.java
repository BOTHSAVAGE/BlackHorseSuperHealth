package com.bothsavage.controller.service.implement;

import com.alibaba.dubbo.config.annotation.Service;
import com.bothsavage.dao.MemberDao;
import com.bothsavage.pojo.Member;
import com.bothsavage.service.MemberService;
import com.bothsavage.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员服务
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    //保存会员信息
    @Override
    public void add(Member member) {
        String password = member.getPassword();
        if(password != null){
            //使用md5将明文密码进行加密
            password = MD5Utils.md5(password);
            member.setPassword(password);
        }
        memberDao.add(member);
    }

    //根据月份查询会员数量
    @Override
    public List<Integer> findMemberCountByMonths(List<String> months) {//2018.05
        List<Integer> memberCount = new ArrayList<>();
        for (String month : months) {
            String date = month + ".31";//2018.05.31
            Integer count = memberDao.findMemberCountBeforeDate(date);
            memberCount.add(count);
        }
        return memberCount;
    }
}
