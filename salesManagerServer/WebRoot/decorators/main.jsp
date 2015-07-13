<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator" %>
<%@ taglib uri="/WEB-INF/sitemesh-page.tld" prefix="page"%> 
<html>
    <head>
        <title><decorator:title default="Welcome!" /></title>
        <decorator:head />
    </head>

    <body>
        <table width="100%" height="100%">
            <tr>
	            <td valign="top">
						<page:applyDecorator page="/jsp/header.html" name="panel" />
				</td>
			</tr>
			<tr>
				
				<td width="100%">
					<table width="100%" height="100%">
						<tr>
							<td id="pageTitle">
								<decorator:title />
							</td>
						</tr>
						<tr>
							<td valign="top" height="100%">
								<decorator:body />
							</td>
						</tr>
						<tr>
							<td id="footer">
								<b>template footer:</b>
								This site is an example site to demonstrate SiteMesh. It serves no other purpose.
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
    </body>
</html>