function toggleSidebar() {
    const sidebar = document.getElementById("sidebar");
    const mainContent = document.querySelector(".main-content");
    sidebar.classList.toggle("collapsed");
    if (sidebar.classList.contains("collapsed")) {
        sidebar.style.width = "60px";
        mainContent.style.marginLeft = "60px";
    } else {
        sidebar.style.width = "250px";
        mainContent.style.marginLeft = "250px";
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