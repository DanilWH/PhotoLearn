<#macro pager url filter page>


<nav aria-label="Pager">
    <ul class="pagination">
        <#assign currPage = page.getNumber() />

        <!-- The "Previous" pager button. -->
        <li class="page-item">
            <#if currPage gt 0>
                <a class="page-link" href="${url}?page=${currPage - 1}">Previous</a>
            <#else>
                <a class="page-link" href="#">Previous</a>
            </#if>
        </li>

        <#list 0..page.getTotalPages() - 1 as p>
            <li class="page-item <#if p == page.getNumber()>active</#if>">
                <a class="page-link" href="${url}?page=${p}<#if filter?exists>&filter=${filter}</#if>">${p + 1}</a>
            </li>
        </#list>

        <!-- The "Next" pager button. -->
        <li class="page-item">
            <#if currPage lt page.getTotalPages() - 1>
                <a class="page-link" href="${url}?page=${currPage + 1}">Next</a>
            <#else>
                <a class="page-link" href="#">Next</a>
            </#if>
        </li>
    </ul>
</nav>

</#macro>