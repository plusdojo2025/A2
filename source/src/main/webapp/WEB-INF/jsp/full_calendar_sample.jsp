<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>勤務カレンダー</title>

  <!-- FullCalendarのCSS -->
  <link href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.10/index.global.min.css" rel="stylesheet" />
  <style>
    #calendar {
      max-width: 900px;
      margin: 50px auto;
    }
  </style>
</head>
<body>
  <h2 style="text-align:center;">勤務スケジュール</h2>
  <div id="calendar"></div>

  <!-- FullCalendarのJS -->
  <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.10/index.global.min.js"></script>

  <script>
    document.addEventListener('DOMContentLoaded', function () {
      const calendarEl = document.getElementById('calendar');

      const calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'timeGridWeek', // 初期表示：週表示
        headerToolbar: {
          left: 'prev,next today',
          center: 'title',
          right: 'dayGridMonth,timeGridWeek' // ← 月・週切り替えボタン
        },
        initialDate: '2025-07-01',
        slotMinTime: "09:00:00",
        slotMaxTime: "18:00:00",
        events: [
          {
            title: '山田勤務',
            start: '2025-07-01T09:00:00',
            end: '2025-07-01T12:00:00'
          },
          {
            title: '佐藤会議',
            start: '2025-07-03T13:00:00',
            end: '2025-07-03T14:00:00',
            color: 'green'
          }
        ]
      });

      calendar.render();
    });
  </script>
</body>
</html>
