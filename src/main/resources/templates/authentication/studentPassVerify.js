const form = document.getElementById('student-form')
const password = document.getElementById('new-student-password')
const pwConfirm = document.getElementById('confirm-new-student-password')
const passphrase = document.getElementById('passphrase')
const errorElement = document.getElementById('student-error')
const passwordExpression = /^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\d]){1,})(?=(.*[\W]){1,})(?!.*\s).{8,20}$/
const passphraseExpression = /^\w{20,50}$/

form.addEventListener('submit', (e) => {
    let messages = []

    let validPassword = passwordExpression.test(String(password.value))
    if (!validPassword) {
        messages.push('Invalid Password. Password must be between 8 and 20 characters, and contain at least 1 uppercase, 1 lowercase, 1 number, and 1 special character.')
    }
    if (password.value != pwConfirm.value) {
        messages.push('Passwords do not match.')
    }
    /*let validPassphrase = passphraseExpression.test(String(passphrase.value))
    if (!validPassphrase) {
        messages.push('Invalid Passphrase. Passphrase must be between 20 to 50 characters and contain only alphanumeric characters.')
    }*/

    if (messages.length > 0) {
        e.preventDefault()
        errorElement.innerText = messages.join(' ')
    }
})
