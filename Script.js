const csvContent = `John Doe,john@example.com
Jane Smith,jane@example.com
Bob Johnson,bob@test.com`;

let data = csvContent.split('\n').map(row => {
  const [name, email] = row.split(',');
  return { name, email, id: crypto.randomUUID() };
});

const tableBody = document.getElementById('tableBody');
const userForm = document.getElementById('userForm');

function render() {
  tableBody.innerHTML = '';

  data.forEach(item => {
    const row = `
      <tr>
        <td>${item.name}</td>
        <td>${item.email}</td>
        <td>
          <button onclick="deleteEntry('${item.id}')">Delete</button>
        </td>
      </tr>
    `;
    tableBody.insertAdjacentHTML('beforeend', row);
  });
}

userForm.addEventListener('submit', (e) => {
  e.preventDefault();
  
  const newEntry = {
    name: document.getElementById('name').value,
    email: document.getElementById('email').value,
    id: crypto.randomUUID()
  };

  data.push(newEntry);
  render();
  userForm.reset();
});

window.deleteEntry = function(id) {
  data = data.filter(item => item.id !== id);
  render();
};

render();