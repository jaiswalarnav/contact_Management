package com.contact.serviceImpl;

import java.util.Date;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.contact.constants.ConstantMessage;
import com.contact.dto.UserDto;
import com.contact.dto.UserRegisterDto;
import com.contact.model.Token;
import com.contact.model.UserRegister;
import com.contact.repository.TokenRepository;
import com.contact.repository.UserRepository;
import com.contact.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	TokenRepository tokenRepository;

	public String loginUser(UserDto userDto) throws Exception {

		Optional<UserRegister> userRegister = userRepository.findById((long) userDto.getId());

		if (!userRegister.isPresent())
			throw new RuntimeException(ConstantMessage.USER_NOT_EXIST);

		if (!bCryptPasswordEncoder.matches(userDto.getPassword(), userRegister.get().getPassword()))
			throw new RuntimeException(ConstantMessage.INVALID_CREDENTIALS);

		Token token = new Token();
		token.setToken(getToken(userDto.getId()));
		return tokenRepository.save(token).getToken();

	}

	public long registerUser(UserRegisterDto userRegisterDto) throws Exception {

		if (!(userRepository.findByName(userRegisterDto.getName()) == null))
			throw new RuntimeException(ConstantMessage.USER_ALREADY_EXIST);

		UserRegister userRegister = new UserRegister();
		modelMapper.map(userRegisterDto, userRegister);

		userRegister.setPassword(bCryptPasswordEncoder.encode(userRegister.getPassword()));

		return userRepository.save(userRegister).getId();

	}

	public String getToken(long id) {

		String userId = "" + id;
		String token = Jwts.builder().setSubject(userId)
				.setExpiration(new Date(System.currentTimeMillis() + 864_000_000))
				.signWith(SignatureAlgorithm.HS512, "MustBeUniqueEverwhere").compact();

		return token;
	}

}
