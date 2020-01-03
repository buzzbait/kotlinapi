package com.buzzbait.kotlinapi.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.stream.Collectors


class JwtUserDetail : UserDetails {
    private var uid: String? = null
    private var password: String? = null


    private val roles: MutableList<String> = mutableListOf<String>()

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return roles.stream().map { role: String? -> SimpleGrantedAuthority(role) }.collect(Collectors.toList())
    }

    override fun getUsername(): String? {
        return uid
    }

    override fun getPassword(): String? {
        return password
    }


    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun setUsername(uid: String?) {
        this.uid = uid
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun getRoles(): List<String>? {
        return roles
    }

    fun addRole(role : String){
        roles.add(role);
    }
}