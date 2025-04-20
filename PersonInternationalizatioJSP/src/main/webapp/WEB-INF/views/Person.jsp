<%-- <!DOCTYPE html>
<html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
   <head>
      <meta charset = "ISO-8859-1"/>
      <title>Person Internationalization</title>
   </head>
   <body>
      <h1><spring:message code="common.hello" /></h1><br>
      Name: <spring:message code="common.name" /><input type='text'/><br>
      Country: <spring:message code="common.country" /><input type='text'/><br>
      Age: <spring:message code="common.age" /><input type='text'/><br>
      <input type="button" value="English">
      <input type="button" value="French">
      <input type="button" value="German">
   </body>
</html> --%>


<!DOCTYPE html>
<html lang="en">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Person Internationalization</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1 class="mb-4"><spring:message code="common.hello" /></h1>

        <div class="form-group row">
		    <label for="name" class="col-sm-3 col-form-label"><spring:message code="common.name" />:</label>
		    <div class="col-sm-2">
		        <input type="text" class="form-control" id="name">
		    </div>
		</div>

        <div class="form-group row">
            <label for="country" class="col-sm-3 col-form-label"><spring:message code="common.country" />:</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" id="country">
            </div>
        </div>

        <div class="form-group row">
            <label for="age" class="col-sm-3 col-form-label"><spring:message code="common.age" />:</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" id="age">
            </div>
        </div>

        <div class="mt-3">
            <button type="button" class="btn btn-primary mr-2" value="English" onclick="changeLanguage('english')"><spring:message code="common.english" /></button>
            <button type="button" class="btn btn-primary mr-2" value="French" onclick="changeLanguage('french')"><spring:message code="common.french" /></button>
            <button type="button" class="btn btn-primary" value="German" onclick="changeLanguage('german')"><spring:message code="common.german" /></button>
        </div>
    </div>
    
   <script>
        function changeLanguage(lang) {
            window.location.href = '/changeLanguage?lang=' + lang;
        }
    </script>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>