<#import "parts/base.ftlh" as base />

<@base.content title="Результаты студентов к уроку ${tutorialDto.title} | Photo Learn">

    <@base.header>Результаты студентов к уроку "${tutorialDto.title}"</@base.header>

    <#if photoResultsDto[0]??>
        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Имя студента</th>
                    <th scope="col">Описание</th>
                    <th scope="col">Результат</th>
                </tr>
            </thead>
            <tbody>
                <#list photoResultsDto as resultDto>
                    <tr>
                        <td>${resultDto.getUser().getUsername()}</td>
                        <td>${resultDto.description}</td>
                        <td>
                            <#assign filename_parts = resultDto.filename?split('.') />
                            <#assign uuid = "uuid-" + filename_parts[0] />
                            <#assign filename = filename_parts[1] + "." + filename_parts[2] />

                            <!-- Link trigger modal -->
                            <a href="/img/${resultDto.filename}"
                               data-toggle="modal"
                               data-target="#${uuid}-Modal"
                            >
                                ${filename}
                            </a>

                            <!-- Modal -->
                            <div class="modal fade" id="${uuid}-Modal" tabindex="-1" aria-labelledby="${uuid}-Label" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered modal-xl">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="${uuid}-Label">${resultDto.getUser().getUsername()}</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <img src="/img/${resultDto.filename}" style="width: 100%; height: 100%"/>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
    <#else>
        <p class="lead">...Ни один студент не добавил результата...</p>
    </#if>

</@base.content>