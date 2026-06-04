const form = document.getElementById('uploadForm');
const moviesContainer = document.getElementById('moviesContainer');

function renderMovies(media) {
    moviesContainer.innerHTML = '';
    media.forEach(media => {
        const card = document.createElement('div');
        card.className = 'media-card';
        card.innerHTML = `
            <img src="${media.imageUrl}" alt="${media.title}">
            <h3>${media.title}</h3>
            <p>Rating: ${media.rating}</p>
            <p>Votes: ${media.votes}</p>
        `;
        moviesContainer.appendChild(card);
    });
}

async function loadMovies() {
    try {
        const res = await fetch('/api/media/list');
        const data = await res.json();
        renderMovies(data);
    } catch (err) {
        console.error('Failed to load media', err);
    }
}

form.addEventListener('submit', async (e) => {
    e.preventDefault();

    const formData = new FormData();

    const file = document.getElementById('file').files[0];
    const title = document.getElementById('title').value;
    const type = document.getElementById('type').value;

    const genreSelect = document.getElementById('genre');
    const genres = Array.from(genreSelect.selectedOptions).map(o => o.value);

    const data = {
        title: title,
        type: type,
        genres: genres
    };

    formData.append('file', file);
    formData.append('data', new Blob(
        [JSON.stringify(data)],
        { type: 'application/json' }
    ));

    try {
        await fetch('/api/media/upload', {
            method: 'POST',
            body: formData
        });

        form.reset();
        loadMovies();
    } catch (err) {
        console.error('Upload failed', err);
    }
});

loadMovies();