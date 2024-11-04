  function toggleSidebar() {
      const sidebar = document.getElementById("sidebar");
      sidebar.classList.toggle("collapsed");
      if (sidebar.style.width === "60px") {
          sidebar.style.width = "250px";
      } else {
          sidebar.style.width = "60px";
      }
  }

  function toggleSection(element) {
      const section = element.nextElementSibling;
      const toggleIcon = element.querySelector('.toggle-icon');

      if (section.style.display === "block") {
          section.style.display = "none";
          toggleIcon.classList.remove('fa-caret-down');
          toggleIcon.classList.add('fa-caret-right');
      } else {
          section.style.display = "block";
          toggleIcon.classList.remove('fa-caret-right');
          toggleIcon.classList.add('fa-caret-down');
      }
  }

  function updateTime() {
        const now = new Date();
        const formattedTime = now.toLocaleDateString('en-GB', {
            day: '2-digit',
            month: '2-digit',
            year: 'numeric'
        }) + ' ' + now.toLocaleTimeString('en-GB', {
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit'
        });
        document.getElementById('server-time-text').textContent = 'Server time - ' + formattedTime;
  }
  setInterval(updateTime, 1000);
  updateTime();

  function confirmLogout(event) {
        event.preventDefault();
        var userConfirmed = confirm("Are you sure you want to log out?");
        if (userConfirmed) {
            window.location.href = event.target.href;
        }
   }