package grails3.example

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String password
    String age
    String location
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	boolean hasPrefs

	// collection for preferences that will store the categories and queries that they are interested in
	static hasMany = [preferences: String, category_ids: String]

	User(String username, String password, String age, String location) {
	  	this()
		this.username = username
		this.password = password
    	this.age = age
    	this.location = location
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}


}
