const fs = require('fs');
const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

// Prompt user for dataset location
rl.question('Enter the dataset file path: ', (filePath) => {
    try {
        const data = fs.readFileSync(filePath, 'utf8');
        // Parse CSV data into arrays
        const rows = data.split('\n').filter(row => row.trim() !== '').map(row => row.split(','));
        const headers = rows[0];

        // MP02: Display first 10 rows
        console.log('\n--- MP02: First 10 Rows ---');
        console.table(rows.slice(0, 11));

        // MP10: Detect duplicate records
        console.log('\n--- MP10: Duplicate Records ---');
        const seen = new Set();
        let duplicates = [];
        rows.forEach((row, index) => {
            const rowStr = row.join(',');
            if (seen.has(rowStr)) {
                duplicates.push(`Row ${index}: ${rowStr}`);
            }
            seen.add(rowStr);
        });
        console.log(duplicates.length > 0 ? duplicates.join('\n') : 'No duplicates found.');

        // MP20: Convert CSV dataset into JSON format
        console.log('\n--- MP20: JSON Format ---');
        const jsonData = rows.slice(1).map(row => {
            let obj = {};
            headers.forEach((header, i) => {
                obj[header.trim()] = row[i] ? row[i].trim() : "";
            });
            return obj;
        });
        console.log(JSON.stringify(jsonData, null, 2));

    } catch (err) {
        console.error("Error: Could not read file. Check the path.");
    } finally {
        rl.close();
    }
});