document.addEventListener("DOMContentLoaded", function() {
    var buttons = document.querySelectorAll('.top');
    var resetButton = document.getElementById('resetButton');
    var resetButton1 = document.getElementById('resetButton1');
    var resetButton2 = document.getElementById('resetButton2');

    buttons.forEach(function (button) {
        button.addEventListener('click', function () {
            buttons.forEach(function (btn) {
                btn.classList.remove('active-button');
            });
            button.classList.add('active-button');
        });
    });

    resetButton.addEventListener('click', function () {
        buttons.forEach(function (btn) {
            btn.classList.remove('active-button');
        });
    });
    resetButton1.addEventListener('click', function () {
        buttons.forEach(function (btn) {
            btn.classList.remove('active-button');
        });
    });
    resetButton2.addEventListener('click', function () {
        buttons.forEach(function (btn) {
            btn.classList.remove('active-button');
        });
    });

    document.getElementById("add-server-button").addEventListener("click", function() {
        resetForm(); // Reset the form first
        document.getElementById("form-heading").innerText = "Server Registration";
        document.getElementById("submit-button").innerText = "Submit";
        document.getElementById("server-form").action = "/convox/servers"; // Set form action for adding a new server
        document.getElementById("server-form-container").style.display = "block";
        document.getElementById("update-server-search").style.display = "none"; // Hide the search section
        document.getElementById("delete-server-search").style.display = "none";
    });

    document.getElementById("update-server-button").addEventListener("click", function() {
        document.getElementById("update-server-search").style.display = "block";
        document.getElementById("server-form-container").style.display = "none"; // Hide the form initially
        document.getElementById("delete-server-search").style.display = "none";
    });

    document.getElementById("delete-server-button").addEventListener("click", function() {
        document.getElementById("delete-server-search").style.display = "block";
        document.getElementById("server-form-container").style.display = "none"; // Hide the form
        document.getElementById("update-server-search").style.display = "none"; // Hide the search sections
    });

    window.resetForm = function() {
        document.getElementById("server-form").reset();
        document.getElementById("server-form-container").style.display = "none";
        document.getElementById("update-server-search").style.display = "none";
        document.getElementById("delete-server-search").style.display = "none";
    };

    window.searchServer = function() {
        var searchValue = document.getElementById("search-server-id").value.toLowerCase();

        var foundServer = servers.find(server =>
            server.serverId.toLowerCase() === searchValue || server.serverName.toLowerCase() === searchValue
        );

        if (foundServer) {
            document.getElementById("server-id").value = foundServer.serverId;
            document.getElementById("server-name").value = foundServer.serverName;
            document.getElementById("database-ip").value = foundServer.databaseIp;
            document.getElementById("database-web-port").value = foundServer.databaseWebPort;
            document.getElementById("voice-ip").value = foundServer.voiceIp;
            document.getElementById("voice-web-port").value = foundServer.voiceWebPort;
            document.getElementById("server-description").value = foundServer.serverDescription;
            document.getElementById("active-status").value = foundServer.activeStatus;
            document.getElementById("form-heading").innerText = "Edit Server";
            document.getElementById("submit-button").innerText = "Update Server";
            document.getElementById("server-form").action = "/convox/servers/" + foundServer.id; // Set form action for update
            document.getElementById("server-form-container").style.display = "block";
        } else {
            alert("Server not found");
        }
    };

    window.deleteSearchServer = function() {
        var searchValue = document.getElementById("delete-search-server-id").value.toLowerCase();
        var foundServer = servers.find(server => server.serverId.toLowerCase() === searchValue || server.serverName.toLowerCase() === searchValue );
        if (foundServer) {
            if (confirm('Are you sure you want to delete this server with ID/Name: ' + searchValue + '?')) {
                deleteServer(foundServer.id);
            }
        }
        else {
            alert("Server not found");
        }
    };

    window.deleteServer = function(id) {
        fetch(`/convox/servers/delete/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                alert('Server deleted successfully');
                location.reload(); // Reload the page to reflect changes
            } else {
                alert('Failed to delete server');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An error occurred while deleting the server');
        });
    };
});