<#import "parts/base.ftlh" as base />

<@base.content title="Уроки | Photo Learn">

	<h1>Уроки</h1>

	<form action="?" method="get">
		<label for="tutorial-search">Найти урок: </label>
		<input id="tutorial-search" type="text" name="tutorial-search"
			   value="${tutorialsSearchValue}" placeholder="Найти урок"/>
		<button type="submit">Найти</button>
		<#if foundTutorialsNumber??>
			<small>${foundTutorialsNumber} results <a href="?">(${totalTutorialsNumber} total)</a></small>
		</#if>
	</form>
	
	<ul>
		<#list tutorials as tutorial>
			<li><div>
					<h2><a href="/tutorial/${tutorial.id}">${tutorial.title}</a></h2>
				</div>
				<hr />
				<div align="right">
					Created: <i>${tutorial.getFormattedCreatedOn(tutorial.createdOn)}</i> -
					Updated: <i>${tutorial.getFormattedCreatedOn(tutorial.updatedOn)}</i> -
					<i>${tutorial.user.getUsername()}</i>
				</div>
			</li>
		</#list>
	</ul>

</@base.content>
