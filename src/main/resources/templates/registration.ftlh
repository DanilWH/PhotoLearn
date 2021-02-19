<#import "parts/base.ftlh" as base />
<#import "spring.ftl" as spring />

<@base.content title="Регистрация | Photo Learn">

	<@base.header>Регистрация</@base.header>

	<form action="/registration" method="post">
		<input type="hidden" name="_csrf" value="${_csrf.token}"/>

		<div class="form-group">
			<label for="username">Имя пользователя: </label>
			<@spring.bind "userDto.username" />
			<@spring.formInput "userDto.username" "class='form-control ${(spring.status.error)?string(\"is-invalid\", \"\")}' aria-describedby='usernameValidation'" />
			<div id="usernameValidation" class="invalid-feedback">
				<@spring.showErrors "<br>" />
			</div>
			<small class="form-text text-muted">
				Required. 150 characters or fewer. Letters, digits and @/./+/-/_ only.
			</small>
		</div>

		<div class="form-group">
			<label for="password">Пароль: </label>
			<@spring.bind "userDto.password" />
			<@spring.formPasswordInput "userDto.password" "class='form-control ${(spring.status.error)?string(\"is-invalid\", \"\")}' aria-describedby='passwordHelp'" />
			<small id="passwordHelp" class="form-text text-muted">
				<ul>
					<li>Пароль не может быть похож на имя пользователя.</li>
					<li>Ваш пароль должен содержать не менее 8 символов.</li>
					<li>Ваш пароль не может состоять только из букв алфавита.</li>
					<li>Ваш пароль не может состоять только из цифр.</li>
				</ul>
			</small>
		</div>

		<div class="form-group">
			<label for="confirmPassword">Повторите пароль: </label>
			<@spring.bind "userDto.password" />
			<@spring.formPasswordInput "userDto.confirmPassword" "class='form-control ${(spring.status.error)?string(\"is-invalid\", \"\")}' aria-describedby='passwordValidation'"/>

			<!-- show errors if the password don't match. -->
			<@spring.bind "userDto" />
			<div id="passwordValidation" class="invalid-feedback">
				<#if spring.status.error>
					<ul><li><@spring.showErrors "<br>" /></li></ul>
				<#else>
					<!-- show error messages for the password. -->
					<@spring.bind "userDto.password" />
					<#if spring.status.error>
						<ul>
							<#assign errors = spring.status.errorMessages[0]?split(',') />
							<#list errors as error>
								<b><li>${error}</li></b>
							</#list>
						</ul>
					</#if>
				</#if>
			</div>
		</div>

		<div class="form-group">
			<label for="email">Email: </label>
			<@spring.bind "userDto.email" />
			<@spring.formInput
					"userDto.email"
					"class='form-control ${(spring.status.error)?string(\"is-invalid\", \"\")}' aria-describedby='emailValidation' placeholder='some@some.some'"
			/>
			<div id="emailValidation" class="invalid-feedback">
				<@spring.showErrors "<br>" />
			</div>
		</div>

		<button id="submit_id" type="submit" class="btn btn-info">Зарегистрироваться</button>
	</form>

</@base.content>