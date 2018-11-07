<div>
    <form action=${path} method="post">
        <div class="form-group">
            <input type="text" class="form-control" name="username" placeholder="username" />
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="password" placeholder="password" />
        </div>
        <div class="form-group">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </div>
        <div class="form-group">
            <button type="submit">${buttonTitle}</button>
        </div>
    </form>
</div>
