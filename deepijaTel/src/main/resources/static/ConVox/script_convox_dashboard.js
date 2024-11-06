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
	const allSections = document.querySelectorAll('.menu-links');
	const allIcons = document.querySelectorAll('.menu-section .toggle-icon');

	// Hide all sections and reset all icons
	allSections.forEach(sec => {
		if (sec !== section) {
			sec.style.display = "none";
		}
	});

	allIcons.forEach(icon => {
		if (icon !== toggleIcon) {
			icon.classList.remove('fa-caret-down');
			icon.classList.add('fa-caret-right');
		}
	});

	if (section.style.display === "block") {
		section.style.display = "none";
		toggleIcon.classList.remove('fa-caret-down');
		toggleIcon.classList.add('fa-caret-right');
	}
	else {
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





//  document.addEventListener("DOMContentLoaded", function() {
//    var dropdowns = document.querySelectorAll("#menu .drop");
//
//    dropdowns.forEach(function(dropdown) {
//      dropdown.addEventListener("click", function(event) {
//        event.preventDefault();
//        var dropdownMenu = this.nextElementSibling;
//
//        if (dropdownMenu.style.display === "block") {
//          dropdownMenu.style.display = "none";
//        } else {
//          dropdownMenu.style.display = "block";
//        }
//      });
//    });
//
//    window.addEventListener("click", function(event) {
//      if (!event.target.matches("#menu .drop")) {
//        var dropdownMenus = document.querySelectorAll("#menu .dropdown_2columns, #menu .dropdown_5columns, #menu .dropdown_7columns");
//
//        dropdownMenus.forEach(function(menu) {
//          menu.style.display = "none";
//        });
//      }
//    });
//  });
