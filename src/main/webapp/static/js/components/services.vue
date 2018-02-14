<script>
	import axios from 'axios'
	import store from './store.vue'
	
	export default {
		saveBook(book) {
			if(book && book.name && book.authorName) {
				axios.post('http://localhost:8080/books', book).then(function(response) {
		            store.dispatch('refreshLivros');
		        })
			}
		},
		removeBooks(bookIds) {
			if(!bookIds || bookIds.length == 0) {
				return;
			}
			axios.delete('http://localhost:8080/books?' + bookIds).then(function(response) {
				store.dispatch('refreshLivros');
			})
		}
	}

</script>