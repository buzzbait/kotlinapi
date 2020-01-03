package com.buzzbait.kotlinapi.security

import com.buzzbait.kotlinapi.model.JwtUserDetail
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*


const val ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60.toLong()
const val SIGNING_KEY = "devglan123r"
const val TOKEN_PREFIX = "Bearer "
const val HEADER_STRING = "Authorization"

@Component
class JwtTokenProvider {
    private val logger = LoggerFactory.getLogger(JwtTokenProvider::class.java);

    fun createToken(userKey :String, roles : List<String>) :String{
        val claims : Claims = Jwts.claims().setSubject(userKey);
        claims["roles"] = roles;

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt( Date(System.currentTimeMillis()) )
                .setExpiration(Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    fun getAuthentication(token : String) : Authentication {
        val claims: Claims = Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).body
        var userName = claims.subject
        val roles = claims["roles"] as List<String>?

        val userDetails = JwtUserDetail()
        userDetails.username = userName

        for (roleValue in roles!!) {
            userDetails.addRole(roleValue);
        }

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities())
    }
}