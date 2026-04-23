document.getElementById('addTutorForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    
    const tutorData = {
        name: document.getElementById('name').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value,
        experience: document.getElementById('experience').value,
        bio: document.getElementById('bio').value
    };
    
    try {
        const response = await fetch('/api/tutors', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(tutorData)
        });
        
        if (!response.ok) throw new Error("Registration failed. Please try again.");
        
        showToast("Registration successful! Redirecting to tutors list...", "success");
        
        // Redirect to tutors page after a short delay
        setTimeout(() => {
            window.location.href = '/tutors.html';
        }, 2000);
        
    } catch (error) {
        showToast(error.message, 'danger');
    }
});

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
