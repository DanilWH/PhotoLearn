<#import "parts/base.ftlh" as base />
<#import "spring.ftl" as spring />

<@base.content title="Регистрация | Photo Learn">

	<h2>Регистрация</h2>
	
	<form action="/registration" method="post">
		<input type="hidden" name="_csrf" value="${_csrf.token}"/>
		<div>
			<label for="username">Имя пользователя: </label>
			<@spring.formInput "userDto.username" />
            <@spring.showErrors "<br>" />
		</div>
		
		<div>
			<label for="password">Пароль: </label>
			<@spring.formPasswordInput "userDto.password" />
			<ul>
				<li>Пароль не может быть похож на имя пользователя.</li>
				<li>Ваш пароль должен содержать не менее 8 символов.</li>
				<li>Ваш пароль не может состоять только из букв алфавита.</li>
				<li>Ваш пароль не может состоять только из цифр.</li>
			</ul>
		</div>
		
		<div>
			<label for="confirmPassword">Повторите пароль: </label>
			<@spring.formPasswordInput "userDto.confirmPassword" />
			
			
			
			<!-- show errors if the password don't match. -->
			<@spring.bind "userDto" />
			<#if spring.status.error>
				<ul><li><@spring.showErrors "<br>" /></li></ul>
			<#else>
				<!-- show error messages for the password. -->
				<@spring.bind "userDto.password" />
				<#if spring.status.error>
				<ul>
					<#assign errors = spring.status.errorMessages[0]?split(',') />
					<#list errors as error>
						<li>${error}</li>
					</#list>
				</ul>
				</#if>
			</#if>
		</div>
		<div><span id="error"></span></div>
		<button id="submit_id" type="submit">Зарегистрироваться</button>
		
	</form>

</@base.content>