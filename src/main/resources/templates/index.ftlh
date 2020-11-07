<#import "parts/base.ftlh" as base />
<#include "parts/user_security.ftlh" />

<@base.content title="Главная | Photo Learn">

	<h2>${(_user.username)?ifExists}</h2>
	
</@base.content>