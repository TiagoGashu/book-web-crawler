<script>
import navigation from './services/navigation.vue'

export default {
	header: 'Favorite Books', 
	navigation: navigation,
	mangas: [],
	livros: [],
	removeAreaVisible: false,
	editing: false,
	alerts: [],
	modalConfirm: { 
		showModal: false
	}
}
</script>