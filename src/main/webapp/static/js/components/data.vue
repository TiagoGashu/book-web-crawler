<script>
	import navigation from './navigation.vue'

    export default {
	   header: 'Favorite Books', 
       navigation: navigation,
	   livros: [],
	   novoLivro: {
		   name: '',
		   authorName: ''
	   },
	   livrosRemover: [],
	   removeAreaVisible: false,
	   editing: false,
	   editLivro: {
		   name: '',
		   authorName: ''
	   }
    }
</script>