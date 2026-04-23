const API_URL = '/api/tutors';
const tutorModal = new bootstrap.Modal(document.getElementById('tutorModal'));

// Load all tutors on start
document.addEventListener('DOMContentLoaded', loadTutors);

// Handle form submission
document.getElementById('tutorForm').addEventListener('submit', saveTutor);

async function loadTutors() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error("Failed to fetch tutors");
        const data = await response.json();
        renderTable(data);
    } catch (error) {
        showToast(error.message, 'danger');
    }
}

async function searchTutors() {
    const query = document.getElementById('searchInput').value;
    if (!query) {
        loadTutors();
        return;
    }

    try {
        const response = await fetch(`${API_URL}/search?name=${encodeURIComponent(query)}`);
        if (!response.ok) throw new Error("Search failed");
        const data = await response.json();
        renderTable(data);
    } catch (error) {
        showToast(error.message, 'danger');
    }
}

function renderTable(tutors) {
    const tbody = document.getElementById('tutorsTableBody');
    tbody.innerHTML = '';

    if (tutors.length === 0) {
        tbody.innerHTML = `<tr><td colspan="6" class="text-center">No tutors found.</td></tr>`;
        return;
    }

    tutors.forEach(tutor => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${tutor.id}</td>
            <td class="fw-bold">${tutor.name}</td>
            <td>${tutor.email}</td>
            <td>${tutor.experience}</td>
            <td>${tutor.bio || '-'}</td>
            <td class="action-btns">
                <button class="btn btn-sm btn-outline-primary" onclick="openEditModal(${tutor.id})">
                    <i class="fas fa-edit"></i> Edit
                </button>
                <button class="btn btn-sm btn-outline-danger" onclick="deleteTutor(${tutor.id})">
                    <i class="fas fa-trash"></i> Delete
                </button>
            </td>
        `;
        tbody.appendChild(row);
    });
}

function openAddModal() {
    document.getElementById('tutorForm').reset();
    document.getElementById('tutorId').value = '';
    document.getElementById('tutorModalLabel').textContent = 'Add New Tutor';
    document.getElementById('password').required = true;
    document.getElementById('passwordHelp').style.display = 'none';
    tutorModal.show();
}

async function openEditModal(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        if (!response.ok) throw new Error("Failed to fetch tutor details");
        const tutor = await response.json();

        document.getElementById('tutorId').value = tutor.id;
        document.getElementById('name').value = tutor.name;
        document.getElementById('email').value = tutor.email;
        document.getElementById('password').value = '';
        document.getElementById('password').required = false;
        document.getElementById('passwordHelp').style.display = 'block';
        document.getElementById('experience').value = tutor.experience;
        document.getElementById('bio').value = tutor.bio;

        document.getElementById('tutorModalLabel').textContent = 'Edit Tutor';
        tutorModal.show();
    } catch (error) {
        showToast(error.message, 'danger');
    }
}

async function saveTutor(e) {
    e.preventDefault();

    const id = document.getElementById('tutorId').value;
    const isEdit = id !== '';

    const tutorData = {
        name: document.getElementById('name').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value,
        experience: document.getElementById('experience').value,
        bio: document.getElementById('bio').value
    };

    try {
        const url = isEdit ? `${API_URL}/${id}` : API_URL;
        const method = isEdit ? 'PUT' : 'POST';

        const response = await fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(tutorData)
        });

        if (!response.ok) throw new Error(isEdit ? "Update failed" : "Creation failed");

        tutorModal.hide();
        loadTutors();
        showToast(isEdit ? "Tutor updated successfully!" : "Tutor added successfully!", "success");
    } catch (error) {
        showToast(error.message, 'danger');
    }
}

async function deleteTutor(id) {
    if (!confirm("Are you sure you want to delete this tutor?")) return;

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });

        if (!response.ok) throw new Error("Deletion failed");

        loadTutors();
        showToast("Tutor deleted successfully!", "success");
    } catch (error) {
        showToast(error.message, 'danger');
    }
}

function showToast(message, type) {
    const toastHtml = `
        <div class="toast align-items-center text-bg-${type} border-0 show" role="alert" aria-live="assertive" aria-atomic="true">
          <div class="d-flex">
            <div class="toast-body">
              ${message}
            </div>
            <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
          </div>
        </div>
    `;
    const container = document.getElementById('toastContainer');
    container.innerHTML = toastHtml;

    setTimeout(() => {
        container.innerHTML = '';
    }, 4000);
}
