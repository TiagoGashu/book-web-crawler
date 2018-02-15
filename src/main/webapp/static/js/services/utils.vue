<script>
	import axios from 'axios'
	import store from '../vuex/store.vue'
	
	export default {
		copyBook(fromBook, toBook) {
			if(fromBook.id == null) {
				toBook.id = null;
			} else {
				toBook.id = fromBook.id;
			}
			toBook.name = fromBook.name;
			
			if(!toBook.authors) {
				toBook.authors = [];
			}
			toBook.authors.splice(0, toBook.authors.length);
			fromBook.authors.forEach(function(a) { toBook.authors.push(a); });
			
			if(!toBook.genres) {
				toBook.genres = [];
			}
			toBook.genres.splice(0, toBook.genres.length);
			fromBook.genres.forEach(function(g) { toBook.genres.push(g); });
		},
		canSaveBook(book) {
			if(!book) return false;
			
			if(!book.name) return false;
			
			if(!(book.authors instanceof Array)) return false;
			if(book.authors.length == 0) return false;
			var unamedAuthors = book.authors.filter((a) => a.name == null || a.name == '');
			if(unamedAuthors.length > 0) return false;

			if(!(book.genres instanceof Array)) return false;
			var unamedGenres = book.genres.filter((g) => g.genre == null || g.genre == '');
			if(unamedGenres.length > 0) return false;
		
			return true;
		},
		isNotEmpty(str) {
			return str && str != '';
		}
	}

</script>