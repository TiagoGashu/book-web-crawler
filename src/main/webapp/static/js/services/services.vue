<script>
	import axios from 'axios'
	import store from '../vuex/store.vue'
	
	export default {
		copyBook(book) { 
			return {
				id: book.id, 
				name: book.name, 
				authorName: book.authorName
			}
		},
		isNotEmpty(str) {
			return str && str != '';
		}
	}

</script>