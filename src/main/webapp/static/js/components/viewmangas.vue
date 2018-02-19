 <template>
 	<div v-show="isActive" class="view-books-comp">
	    <div style="max-height: 80%; overflow: auto;">
	    
	        <ul class="list-group" :class="{'editing' : _editing}">
	        
                <li class="list-group-item" v-for="manga in mangas" :class="{'active': manga.checked }"> 
                    <div class="checkbox"> 
                        <input type="checkbox" v-model="manga.checked" @change="cancelEditing">
                        
                        {{ manga.name }} - 
                        <small v-for="author in manga.authors">
                        	{{ author.name }}
                        </small>
                        <span>id: {{manga.id}}</span>
                        
                        <span v-if="canEdit(manga)" @click="toggleEdit" :title="editButtonTitle" class="button">
                        	<i class="material-icons">create</i>
                        </span>
                        <span v-if="canRemove(manga)" @click="remove(manga)" title="remove manga" class="button">
                        	<i class="material-icons">delete_sweep</i>
                        </span>
                    </div>
                    <div class="editArea" v-if="editing(manga)">
                    	<edit-manga :mangaEditing="manga"></edit-manga>
                    </div>
                </li>
                
            </ul>
            
            <div class="remove-all button" v-if="moreThanOne()">
            	<i @click="removeAll" class="material-icons md-48" :title="removeAllTitle">delete_sweep</i>
            </div>
        </div>
    </div>
 </template>
 
 <script>
 	/** componente para exibição e seleção de mangas */
 	import C from '../constants.vue'
 	import store from '../vuex/store.vue'
 	import navigationmix from '../services/navigationmix.vue'
 	
 	var data = {
 		componentName: C.components.ViewMangas
 	}
 	
 	export default {
 		mixins: [navigationmix],
 		data() {
 			return data;
 		},
 		methods: {
 			onlyOne() {
 				return this.$store.getters.selectedMangas.length == 1
 			},
 			moreThanOne() {
 				return this.$store.getters.selectedMangas.length > 1;
 			},
 			canEdit(manga) {
 				return this.onlyOne() && manga.checked;
 			},
 			canRemove(manga) {
 				return this.onlyOne() && manga.checked;
 			},
 			cancelEditing() {
 				if(!this.onlyOne()) {
 					this.$store.dispatch('stopEditing');	
 				}
 			},
 			toggleEdit() {
 				this.$store.dispatch('toggleEditManga');
 			},
			editing(manga) {
				return this.$store.getters.editing && manga.checked;	
			},
			remove(manga) {
				this.$store.dispatch('removeManga', manga);
			},
 			removeAll() {
 				this.$store.dispatch('removeMangas');
 			}
 		},
		computed: {
			mangas() {
				return this.$store.getters.mangas;
			},
			_editing() {
				return this.$store.getters.editing;
			},
			editButtonTitle() {
				return this._editing ? 'stop editing' : 'edit manga';
			},
			removeAllTitle() {
				return 'delete all selected: ' + this.$store.getters.selectedMangas.map(el => el.name).join(',');
			}
		},
		mounted: function() {
			store.dispatch('refreshMangas')
		}
 	}
 </script>
 
<style>
	.view-books-comp .list-group-item .button {
		position: relative;
		top: 4px;
	}
	.md-48 {
		font-size: 48px;
	}
	.view-books-comp .editing .list-group-item.active {
		background-color: #b4b9bf;
	}
	.list-group .button {
		cursor: pointer;
	}
	.remove-all {
		position: absolute;
		margin-top: 11px;
    	right: 13px;
    	cursor: pointer;
	}
 </style>