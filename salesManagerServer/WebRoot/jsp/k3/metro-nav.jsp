<html>
<head>
/* 
Inspired by http://dribbble.com/shots/890759-Ui-Kit-Metro/attachments/97174
*/
body {
    background: url(http://habrastorage.org/files/90a/010/3e8/90a0103e8ec749c4843ffdd8697b10e2.jpg);
}
.nav-row {
	text-align: center;
}
.nav-row p {
	padding: 5px;
}
.nav-row .col-md-2 {
	background-color: #fff;
	border: 1px solid #e0e1db;
	border-right: none;
}
.nav-row .col-md-2:last-child {
	border: 1px solid #e0e1db;
}
.nav-row .col-md-2:first-child {
	border-radius: 5px 0 0 5px;
}
.nav-row .col-md-2:last-child {
	border-radius: 0 5px 5px 0;
}
.nav-row .col-md-2:hover {
	color: #e92d00;
    cursor: pointer;
}
.nav-row .active {
	color: #e92d00;
	margin-top: -6px;
	border-top: 6px solid #e92d00;
	border-bottom: 6px solid #e92d00;
}
.nav-row .active:before {
	content: '';
	position: absolute;
	border-style: solid;
	border-width: 6px 6px 0;
	border-color: #e92d00 transparent;
	display: block;
	width: 0;
	z-index: 1;
	margin-left: -6px;
	top: 0;
	left: 50%;
}
.nav-row .glyphicon {
	padding-top: 15px;
	font-size: 40px;
}
</head>
<body>
<!--
Inspired by http://dribbble.com/shots/890759-Ui-Kit-Metro/attachments/97174
-->
<div class="container" style="margin-top:100px;">
    <div class="row">
		<div class="row nav-row">
			<div class="col-md-2">
				<span class="glyphicon glyphicon-folder-close"></span>
				<p>Shopping</p>
			</div>
			<div class="col-md-2 active">
				<span class="glyphicon glyphicon-calendar"></span>
				<p>Calendar</p>
			</div>
			<div class="col-md-2">
				<span class="glyphicon glyphicon-globe"></span>
				<p>Network</p>
			</div>
			<div class="col-md-2">
				<span class="glyphicon glyphicon-leaf"></span>
				<p>Ecology</p>
			</div>
			<div class="col-md-2">
				<span class="glyphicon glyphicon-time"></span>
				<p>Statistics</p>
			</div>
			<div class="col-md-2">
				<span class="glyphicon glyphicon-bell"></span>
				<p>Events</p>
			</div>
		</div>
    </div>
</div>
</body>