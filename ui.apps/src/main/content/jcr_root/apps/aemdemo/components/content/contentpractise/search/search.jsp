<%-- <%@include file="/libs/foundation/global.jsp"%>
<%@page import="javax.jcr.query.QueryResult,javax.jcr.query.Query,javax.jcr.Node,javax.jcr.NodeIterator,org.apache.commons.lang.StringEscapeUtils"%>

<%

String queryString=(slingRequest.getParameter("q")!=null)?StringEscapeUtils.escapeXml(slingRequest.getParameter("q")) :"";
%>


<center>

<form action="<%=currentPage.getPath() %>.html">

<input name="q" value="<%=queryString %>"/>
<input value="search" type="submit"/>


</form>

</center>


<br>

if(slingRequest.getParameter("q")!=null){



 --%>