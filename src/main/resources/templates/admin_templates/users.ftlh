<#import "../parts/base.ftlh" as base />

<@base.content title="Пользователи | Photo Learn">

    <h1>Список пользователей</h1>

    <table>
        <thead>
            <th>ID</th>
            <th>Username</th>
            <th>User Roles</th>
            <th></th>
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