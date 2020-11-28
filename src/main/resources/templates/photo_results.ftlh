<#import "parts/base.ftlh" as base />

<@base.content title="Резултаты студентов к уроку ... | Photo Learn">

    <h1>Результаты студентов к уроку ...</h1>
    <table>
        <thead>
            <tr>
                <th>Имя студента</th>
                <th>Описание</th>
                <th>Результат</th>
            </tr>
        </thead>
            <#list photoResultsDto as resultDto>
                <tr>
                    <td>${resultDto.getUser().getUsername()}</td>
                    <td>${resultDto.description}</td>
                    <td><a href="#">${resultDto.filename}</a></td>
                </tr>
            </#list>
        </tbody>
    </table>

</@base.content>