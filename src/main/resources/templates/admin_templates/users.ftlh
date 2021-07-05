<#import "../parts/base.ftlh" as base />

<@base.content title="Пользователи | Photo Learn">

    <@base.header>Список пользователей</@base.header>

    <table class="table table-hover" id="users-list-table">
        <thead class="thead-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Username</th>
                <th scope="col">User Roles</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <#list usersDto as singleUser>
                <tr>
                    <td>${singleUser.id}</td>
                    <td>${singleUser.username}</td>
                    <td><#list singleUser.getUserRoles() as userRole>${userRole}<#sep>, </#list></td>
                    <td><a href="/admin/user/${singleUser.id}/edit">Редактировать пользователя</a></td>
                </tr>
            </#list>
        </tbody>
    </table>

</@base.content>