export default function ActivityTypes({selected, onChange}) {

    function handleCheckboxClick(e) {
        const {checked, name} = e.target;
        if (checked) {
            onChange([...selected, name])
        } else {
            onChange([...selected.filter(selectedName => selectedName !== name)])
        }

        
    }

    return (
        <>
            <label className="border p-4 flex rounded-2xl gap-2 items-center cursor-pointer">
                <input type="checkbox" checked={selected.includes("shopping")} name="shopping" onChange={handleCheckboxClick}/>
                <span>Shopping</span>
            </label>

            <label className="border p-4 flex rounded-2xl gap-2 items-center cursor-pointer">
                <input type="checkbox" checked={selected.includes("nightlife")} name="nightlife" onChange={handleCheckboxClick}/>
                <span>Nightlife</span>
            </label>

            <label className="border p-4 flex rounded-2xl gap-2 items-center cursor-pointer">
                <input type="checkbox" checked={selected.includes("museums")} name="museums" onChange={handleCheckboxClick}/>
                <span>Museums</span>
            </label>
        </>
    );
}