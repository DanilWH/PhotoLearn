<#import "parts/base.ftlh" as base />
<#import "spring.ftl" as spring />

<@base.content title="Регистрация | Photo Learn">

	<script>
		function check() {
			var password = document.getElementById("password").value;
			var confirm_password = document.getElementById("confirm_password_id").value;
				
			if (password == confirm_password) {
				return true;
			} else {
			    document.getElementById('error').style.color = 'red';
			    document.getElementById('error').innerHTML = 'not matching';
			    return false;
			}
		}
	</script>

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
			<label for="confirm_password_id">Повторите пароль: </label>
			<input id="confirm_password_id" type="password" name="confirm_password" />
			<#if spring.status.errorMessages[0]??>
			<ul>
				<#assign errors = spring.status.errorMessages[0]?split(',') />
				<#list errors as error>
					<li>${error}</li>
				</#list>
			</ul>
			</#if>
		</div>
		<div><span id="error"></span></div>
		<button id="submit_id" type="submit" onclick="return check();">Зарегистрироваться</button>
		
	</form>

</@base.content>