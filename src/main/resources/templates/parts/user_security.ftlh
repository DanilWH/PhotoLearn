<#assign know = Session.SPRING_SECURITY_CONTEXT?? />

<#if know>
	<#assign _user = Session.SPRING_SECURITY_CONTEXT.authentication.principal />
</#if>