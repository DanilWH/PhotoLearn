<#macro tutorial_editor action tutorialDto titleValue="" contentValue="">

    <form action="${action}" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <div class="form-group">
            <label for="title">Заголовок</label>
            <@spring.bind "tutorialDto.title" />
            <@spring.formInput "tutorialDto.title" "class='form-control ${(spring.status.error)?string(\"is-invalid\", \"\")}' aria-describedby='titleValidation'" />
            <div id="titleValidation" class="invalid-feedback">
                <@spring.showErrors "<br>" />
            </div>
        </div>
        <div class="form-group">
            <label for="content">Содержимое</label>
            <@spring.bind "tutorialDto.content" />
            <@spring.formTextarea "tutorialDto.content" "class='form-control ${(spring.status.error)?string(\"is-invalid\", \"\")}' aria-describedby='contentValidation'" />
            <div id="contentValidation" class="invalid-feedback">
                <@spring.showErrors "<br>" />
            </div>
        </div>

        <!-- The "File uploading" form. -->
        <div class="custom-file">
            <input id="multipartFile"
                   name="multipartFile"
                   class="custom-file-input"
                   type="file"
                   accept="image/*"
                   onchange="return imagePreview();"
            />
            <label id="fileLabel" class="custom-file-label" for="multipartFile" data-browse="Найти">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-folder2-open" viewBox="0 0 16 16">
                    <path fill-rule="evenodd" d="M1 3.5A1.5 1.5 0 0 1 2.5 2h2.764c.958 0 1.76.56 2.311 1.184C7.985 3.648 8.48 4 9 4h4.5A1.5 1.5 0 0 1 15 5.5v.64c.57.265.94.876.856 1.546l-.64 5.124A2.5 2.5 0 0 1 12.733 15H3.266a2.5 2.5 0 0 1-2.481-2.19l-.64-5.124A1.5 1.5 0 0 1 1 6.14V3.5zM2 6h12v-.5a.5.5 0 0 0-.5-.5H9c-.964 0-1.71-.629-2.174-1.154C6.374 3.334 5.82 3 5.264 3H2.5a.5.5 0 0 0-.5.5V6zm-.367 1a.5.5 0 0 0-.496.562l.64 5.124A1.5 1.5 0 0 0 3.266 14h9.468a1.5 1.5 0 0 0 1.489-1.314l.64-5.124A.5.5 0 0 0 14.367 7H1.633z"/>
                </svg>
                Выбрать фотографию для редактирования
            </label>
        </div>

        <!-- Show the image on uploading. -->
        <div class="form-group">
            <div id="imagePreview" class=""></div>
            <script src="/js/image_preview.js"></script>
        </div>

        <button type="submit" class="btn btn-info mb-3">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-earmark-richtext-fill" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M2 2a2 2 0 0 1 2-2h5.293A1 1 0 0 1 10 .293L13.707 4a1 1 0 0 1 .293.707V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2zm7.5 1.5v-2l3 3h-2a1 1 0 0 1-1-1zM7 6.25a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0zm-.861 1.542l1.33.886 1.854-1.855a.25.25 0 0 1 .289-.047l1.888.974V9.5a.5.5 0 0 1-.5.5H5a.5.5 0 0 1-.5-.5V9s1.54-1.274 1.639-1.208zM5 11a.5.5 0 0 0 0 1h6a.5.5 0 0 0 0-1H5zm0 2a.5.5 0 0 0 0 1h3a.5.5 0 0 0 0-1H5z"/>
            </svg>
            Сохранить
        </button>
    </form>

    <!-- Load the TinyMCE text editor. -->
    <#include "tinymce_load.ftlh" />

</#macro>