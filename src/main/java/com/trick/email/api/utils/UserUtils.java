package com.trick.email.api.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;

import com.trick.email.api.domain.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public final class UserUtils {

	private final static SecretKey CHAVE = Keys.hmacShaKeyFor(
			"7f-j&CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&RNbDHUX6".getBytes(StandardCharsets.UTF_8));

	public static String md5(String senha) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		BigInteger hash = new BigInteger(1, messageDigest.digest(senha.getBytes()));
		return hash.toString(16);
	}

	public static String generateToken(User user) {
		String jwtToken = Jwts.builder()
			    .setSubject(user.getName())
			    .setIssuedAt(new Date())
			    .setExpiration(
			        Date.from(
			            LocalDateTime.now().plusDays(3L)
			                .atZone(ZoneId.systemDefault())
			            .toInstant()))
			    .signWith(SignatureAlgorithm.HS512, CHAVE)
			    .compact();
		return jwtToken;

	}
}