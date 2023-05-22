const formatTitle = (title) => {
	return capitalize(title.slice(9))
};

export default formatTitle;

const capitalize = (string) => {
	return string.charAt(0).toUpperCase() + string.slice(1);
}