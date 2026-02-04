const csvContent = `John Doe,john@example.com
Jane Smith,jane@example.com
Bob Johnson,bob@test.com`;

let data = csvContent.split('\n').map(row => {
  const [name, email] = row.split(',');
  return { name, email, id: crypto.randomUUID() };
});

const tableBody = document.getElementById('tableBody');
const actionLog = document.getElementById('actionLog');
const userForm = document.getElementById('userForm');

function addLog(message) {
  const time = new Date().toLocaleTimeString();
  const logItem = `<li class="log-item"><span>${message}</span> <small>${time}</small></li>`;
  actionLog.insertAdjacentHTML('afterbegin', logItem);
}

function render() {
  tableBody.innerHTML = '';
  data.forEach(item => {
    const row = `
      <tr>
        <td><strong>${item.name}</strong></td>
        <td>${item.email}</td>
        <td><button class="btn-del" onclick="deleteEntry('${item.id}', '${item.name}')">Delete</button></td>
      </tr>`;
    tableBody.insertAdjacentHTML('beforeend', row);
  });
}

userForm.addEventListener('submit', (e) => {
  e.preventDefault();
  const name = document.getElementById('name').value;
  const email = document.getElementById('email').value;
  
  data.push({ name, email, id: crypto.randomUUID() });
  addLog(`Added user: ${name}`);
  render();
  userForm.reset();
});

window.deleteEntry = function(id, name) {
  data = data.filter(item => item.id !== id);
  addLog(`Removed user: ${name}`);
  render();
};

render();