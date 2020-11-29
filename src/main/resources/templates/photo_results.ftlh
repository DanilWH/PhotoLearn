<#import "parts/base.ftlh" as base />

<@base.content title="Резултаты студентов к уроку ${tutorialDto.title} | Photo Learn">

    <h1>Результаты студентов к уроку "${tutorialDto.title}"</h1>
    <#if photoResultsDto[0]??>
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
                        <td><a href="/img/${resultDto.filename}">${resultDto.filename}</a></td>
                    </tr>
                </#list>
            </tbody>
        </table>
    <#else>
        <p>...Ни один студент не добавил результата...</p>
    </#if>

</@base.content>