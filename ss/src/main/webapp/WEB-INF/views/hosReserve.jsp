<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr@4.6.9/dist/flatpickr.min.css">

    <style>
        #form {
            width : 350px;
            height : 270px;
            border : 1px solid black;
            border-radius : 12px;
        }
        .line {
            text-align : center;
        }
        .warning {
        	color : red;
        }
    </style>
</head>
<body>
    <form id="form" action="/reserves_hospital/check" method="POST">
        <legend style = "font-size : 22px; text-align : center;">${hos.hostitle} 예약</legend>
        <div class = "line">
        <input type = "hidden" value = "${hos.code}" name = "code">
        <input type = "hidden" value = "${mb.id}" name = "id">
        동물종류: <input type = "text" name = "dogtype" maxlength="20" required><br>
        ex) 강아지,고양이,도마뱀 등<br>
        예약사유: <select name = "h_pro">
            <option value = "질병">질병</option>
            <option value = "상해">상해</option>
            <option value = "미용">미용</option>
        </select><br>
        기간선택: <input type="date" id="datePicker" name="datePicker"><br>
        <label for="minSelect">시간선택:</label>
        <select id="minSelect" name="minSelect"></select><br>
        <div id="timeError" style="color: red; display: none;"></div>
        진료동물: 
        <select name = "animal" id = "animal">
	    <c:if test="${empty pro.animal}">
	        <option id="none">없음</option>
	    </c:if>
        <c:forEach items="${pro.animal}" var="animal">
        	<option>${animal}</option>
        </c:forEach>
        </select>
        <div class = "warning">※ 진료동물 선택을 위한 동물 프로필이 존재하지 않을경우 반려동물에 대한 프로필 생성이 필요합니다.</div>
        <input type="submit">
        <input type="hidden" id="checkdate" name="checkdate" value="">
        </div>
    </form>
	
    <script>
    // Initialize Flatpickr date picker
    const datePicker = flatpickr("#datePicker", {
        minDate: "today", // Set minimum date to today
        enableTime: false,
        maxDate: new Date().fp_incr(14),
        dateFormat: "Y-m-d",
    });

    const minHour = 9;
    const maxHour = 18;
    const minMin = 0;
    const maxMin = 30;
    const interval = 45;

    // Populate minute select options
    const minSelect = document.getElementById("minSelect");
    populateMinuteSelect(minHour);

    // Event listener for date selection
    document.getElementById("datePicker").addEventListener("change", function() {
        populateMinuteSelect();
    });

    //const reservedTimes = [
<%--         <% for (String reservedTime : ${hoslist.checkDateTime}) { %> --%>
<%--             "<%= reservedTime %>", --%>
<%--         <% } %> --%>
    //];

    const reservedTimes = [
        "2023-08-24 09:00:00", "2023-08-24 09:45:00", "2023-08-24 10:30:00", "2023-08-25 09:00:00"
    ];

    // ...

	// Function to populate minute select options
	function populateMinuteSelect() {
	    minSelect.innerHTML = "";
	
	    const selectedDate = document.getElementById("datePicker").value; // Get selected date
	
	    if (!selectedDate) {
	        return; // If no date is selected, do nothing
	    }
	
	    const startHourInMinutes = minHour * 60 + minMin;
	    const endHourInMinutes = maxHour * 60 + maxMin;
	
	    for (let minutes = startHourInMinutes; minutes <= endHourInMinutes; minutes += interval) {
	        const hour = Math.floor(minutes / 60);
	        const minute = minutes % 60;
	
	        const checkdate = new Date(selectedDate);
	        checkdate.setHours(hour);
	        checkdate.setMinutes(minute);
	
	        const time = (hour < 10 ? "0" : "") + hour + ":" + (minute < 10 ? "0" : "") + minute;
	
	        const yearMonthDay = checkdate.toISOString().substr(0, 10);
	        const reservedTime = yearMonthDay + " " + time + ":00"; // Include seconds
	
	        // Check if the reservedTimes array includes the reservedTime
	        if (!reservedTimes.includes(reservedTime)) {
	            const option = document.createElement("option");
	            option.value = time;
	            option.textContent = time;
	            minSelect.appendChild(option);
	        }
	    }
	}

    document.getElementById("form").addEventListener("submit", function(event) {
        const selectedDate = document.getElementById("datePicker").value;
        const selectedMinInMinutes = minSelect.value.split(":").map(part => parseInt(part)).reduce((acc, val, idx) => idx === 0 ? acc + val * 60 : acc + val, 0);

        if (!selectedDate || isNaN(selectedMinInMinutes)) {
            alert("년월일과 시간을 선택해주세요.");
            event.preventDefault();
            return;
        }

        const selectedHour = Math.floor(selectedMinInMinutes / 60);
        const selectedMin = selectedMinInMinutes % 60;

        const selectedDateTime = new Date(selectedDate);
        selectedDateTime.setHours(selectedHour);
        selectedDateTime.setMinutes(selectedMin);

        const now = new Date();

        const startTime = new Date(selectedDate);
        startTime.setHours(minHour);
        startTime.setMinutes(minMin);
        startTime.setSeconds(0);

        const endTime = new Date(selectedDate);
        endTime.setHours(maxHour);
        endTime.setMinutes(maxMin);
        endTime.setSeconds(0);

        if (selectedDateTime < now || selectedDateTime < startTime || selectedDateTime > endTime) {
            const errorDiv = document.getElementById("timeError");
            if (selectedDateTime > endTime) {
                errorDiv.textContent = "올바른 시간과 날짜를 선택해주세요.";
            } else {
                errorDiv.textContent = "올바른 시간과 날짜를 선택해주세요.";
            }
            errorDiv.style.display = "block";
            setTimeout(function() {
                errorDiv.style.display = "none";
            }, 3000);
            event.preventDefault();
        } else if ($('#animal').val() === "없음") {
            alert('지정된 동물이 존재하지 않습니다.');
            event.preventDefault();
        } else {
            const checkdate = selectedDate + " " + (selectedHour < 10 ? "0" : "") + selectedHour + ":" + (selectedMin < 10 ? "0" : "") + selectedMin + ":00";
            document.getElementById("checkdate").value = checkdate;

            alert("선택된 시간: " + checkdate);
        }
    });
</script>
</body>
</html>