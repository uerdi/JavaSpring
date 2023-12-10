<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/6/2023
  Time: 4:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Title</title>
    <!-- for Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <!-- YOUR own local CSS -->
    <!-- For any Bootstrap that uses JS -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<h1>Send an Omikuji!</h1>
<form action="/submitForm" method="post">
    <div class="conter">
        <label>Pick ane number from 5 to 25</label>
        <select name="number" >
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option>
            <option value="13">13</option>
            <option value="14">14</option>
            <option value="15">15</option>
            <option value="16">16</option>
            <option value="17">17</option>
            <option value="18">18</option>
            <option value="19">19</option>
            <option value="20">20</option>
            <option value="21">21</option>
            <option value="22">22</option>
            <option value="23">23</option>
            <option value="24">24</option>
            <option value="25">25</option>
        </select>
    </div>
    <div>
        <label> Enter the name of any city</label>
        <input type="text" name="city">
    </div>
    <div>
        <label> Enter the name of any real person</label>
        <input type="text" name="person">
    </div>
    <div>
        <label> Enter professional endeavor or hobby</label>
        <input type="text" name="hobby">
    </div>
    <div>
        <label> Enter any type of living thing</label>
        <input type="text" name="livingThing">
    </div>
    <div>
        <label> Say something nice to someone</label>
        <textarea name="message"cols="30" rows="4"></textarea>
    </div>
    <button type="submit">Send</button>
</form>

</body>
</html>
