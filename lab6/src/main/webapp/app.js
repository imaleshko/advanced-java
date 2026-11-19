document.addEventListener("DOMContentLoaded", () => {
    const contactForm = document.getElementById("contact-form");
    const errorBox = document.getElementById("form-error");
    if (contactForm) {
        contactForm.addEventListener("submit", (e) => {
            const sender = contactForm.elements.sender.value.trim();
            const email = contactForm.elements.email.value.trim();
            const message = contactForm.elements.message.value.trim();

            if (!sender || !email.includes("@") || !message) {
                e.preventDefault();
                errorBox.textContent = "Заповніть ім'я, коректний email та текст повідомлення.";
                errorBox.hidden = false;
            }
        });
    }
});
