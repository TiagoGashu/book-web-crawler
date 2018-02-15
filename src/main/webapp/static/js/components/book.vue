<template>
<div class="editBook">
    <div class="input-group">
        <div class="input-group-prepend mb-3">
        	<span class="input-group-text">Book&apos;s name</span>
        </div>
        <input v-model="book.name" type="text" class="form-control" style="height: 38px;"/>
    </div>
    
    <div class="input-group" v-for="author in book.authors">
        <div class="input-group-prepend mb-3">
            <span class="input-group-text">Author&apos;s name</span>
        </div>
        <input v-model="author.name" type="text" 
        	class="form-control" style="height: 38px;"/>
       	<div class="input-group-append">
     		<button class="btn btn-outline-secundary" @click="removeAuthor(author)">-</button>	
     	</div>
    </div>
    <div class="add-button-container">
    	<!-- add author btn -->
     	<div class="add-button">
     		<button class="btn btn-primary btn-block" @click="addAuthor">add author</button>
     	</div>
    </div>
    
    <div class="input-group" v-for="genre in book.genres">
        <div class="input-group-prepend mb-3">
            <span class="input-group-text">Genre</span>
        </div>
        <input v-model="genre.genre" type="text" 
        	class="form-control" style="height: 38px;"/>
       	<div class="input-group-append">
     		<button class="btn btn-outline-secundary" @click="removeGenre(genre)">-</button>	
     	</div>
    </div>
    <div class="add-button-container">
    	<!-- add genre btn -->
     	<div class="add-button">
     		<button class="btn btn-primary btn-block" @click="addGenre">add genre</button>
     	</div>
    </div>
</div>
</template>

<script>
	import store from '../vuex/store.vue'
	
	export default {
		data() {
			return this.book;
		},
		props: ['book'],
		methods: {
			addAuthor() {
				this.book.authors.push({name: ''});
			},
			removeAuthor(author) {
				this.book.authors.splice(this.authors.indexOf(author), 1);
			},
			addGenre() {
				this.book.genres.push({genre: ''});
			},
			removeGenre(genre) {
				this.book.genres.splice(this.genres.indexOf(genre), 1);
			}
		}
	}
</script>

<style>
	.editBook .input-group-append {
		height: 38px;
	}
	.editBook .add-button {
		margin-top: 4px;
		margin-bottom: 4px;
	}
</style>