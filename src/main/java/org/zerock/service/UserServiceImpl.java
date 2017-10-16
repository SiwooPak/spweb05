package org.zerock.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.zerock.domain.UserVO;
import org.zerock.dto.LoginDTO;
import org.zerock.persistence.UserDAO;
import org.zerock.util.SHAUtil;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO dao;

	@Override
	public UserVO login(LoginDTO dto) throws Exception {

		String salt = dao.getSalt(dto.getUid());
		
		System.out.println("=================================");
		System.out.println("salt: " +salt);
		
		if(salt == null) {
			throw new Exception("ERROR");
		}
		
		String encryptPW = SHAUtil.getEncrypt(dto.getUpw(), salt);
		
		dto.setUpw(encryptPW);
		
		UserVO vo =  dao.login(dto);
		
		if(vo == null || vo.getUid() == null) {
			throw new Exception("ERROR2.........");
		}
		
		return vo;
	}

	@Override
	public void keepLogin(String uid, String sessionId, Date next) throws Exception {

		dao.keepLogin(uid, sessionId, next);

	}

	@Override
	public UserVO checkLoginBefore(String value) {

		return dao.checkUserWithSessionKey(value);
	}

	@Override
	public void join(UserVO vo) throws Exception {
		
		dao.join(vo);
	}
}
